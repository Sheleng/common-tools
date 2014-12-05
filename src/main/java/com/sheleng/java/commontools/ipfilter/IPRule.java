package com.sheleng.java.commontools.ipfilter;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IPRule
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class IPRule {
    /* IP正则表达式 */
    private static final String IP_REGEX =
            "^((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?).){3}(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?)$";

    private static final String IP_BLOCK_REGEX = "^2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?$";

    private static final Map<String, Pattern> IP_PATTERN = new HashMap<String, Pattern>();

    static interface IPPattern {
        boolean isMatch(String ip);
    }

    /**
     * 数字,只能小于256
     */
    static class NumPattern implements IPPattern {
        int source;

        public NumPattern(String string) {
            source = Integer.valueOf(string);
        }

        public boolean isMatch(String ip) {
            return source == Integer.valueOf(ip);
        }
    }

    /**
     * 只能包含一个"-"符号，并且在前后都是小于256，前面数字比后面数字小
     */
    static class SegmentPattern implements IPPattern {
        int begin;
        int end;

        public SegmentPattern(String string) {
            String[] temp = StringUtils.split(string, '-');
            begin = Integer.parseInt(temp[0]);
            end = Integer.parseInt(temp[1]);
        }

        public boolean isMatch(String ip) {
            int ippart = Integer.valueOf(ip);
            return begin <= ippart && ippart <= end;
        }
    }

    /**
     * 只能包含一个*并且在结尾。
     */
    static class StarPattern implements IPPattern {
        String starString;

        public StarPattern(String ipRegexString) {
            starString = StringUtils.replace(ipRegexString, "*", "");
        }

        public boolean isMatch(String ip) {
            return ip.startsWith(starString);
        }
    }

    static class IPRegex implements IPPattern {
        IPPattern[] patterns = new IPPattern[4];

        public IPRegex(String ip) {
            String[] tmps = StringUtils.split(ip, '.');
            for (int i = 0; i < tmps.length; i++) {
                if (tmps[i].contains("-")) {
                    patterns[i] = new SegmentPattern(tmps[i]);
                } else if (tmps[i].contains("*")) {
                    patterns[i] = new StarPattern(tmps[i]);
                } else {
                    patterns[i] = new NumPattern(tmps[i]);
                }
            }
        }

        public boolean isMatch(String ip) {
            String tmps[] = StringUtils.split(ip, '.');
            for (int i = 0; i < tmps.length; i++) {
                if (!patterns[i].isMatch(tmps[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    static class Regex {
        static boolean isMatch(String ip, String ipRegexString) {
            Pattern pattern = IP_PATTERN.get(ipRegexString);
            if (pattern == null) {
                pattern = Pattern.compile(ipRegexString);
                IP_PATTERN.put(ipRegexString, pattern);
            }
            Matcher m = pattern.matcher(ip);
            return m.matches();
        }
    }

    /**
     * 判断指定的IP是否符合指定的规则(三个特殊符号 - ? *),例如：
     * [192.*.1.236-239:yes;192.*.1.226:no;218.85.*.*:no]最后一个不要加";"分号
     * 其中，前面的规则优先级高
     * 规则中的 - ? * 不能同时存在于同一个段内 如: 192.168.*?.123 会抛出异常。
     * 特殊符号在同一段内只能有一个, 如 192.16*.1.*,  192.1**.1.1 是错误的，但是可以用?号代替
     */
    public static boolean isAllowIP(String rule, String ip) throws Exception {
        String[] ruleArray = StringUtils.split(rule, ";");
        return isAllowIP(ruleArray, ip);
    }

    public static boolean isAllowIP(String[] ruleArray, String ip) throws Exception {
        // 如果IP地址是错的，禁止
        if (!Regex.isMatch(ip, IP_REGEX)) {
            throw new Exception("参数ip错误，错误的IP地址：" + ip + "。");
        }

        // 分离规则
        String[] ipdata = StringUtils.split(ip, ".");
        boolean retValue = false;//默认返回值

        // 遍历规则并验证
        for (String s : ruleArray) {
            boolean isFind = false;
            String[] data = StringUtils.split(s, ':');
            // 如果没有用:分开
            if (data.length != 2) {
                System.out.println("Rule = " + s + ",len = " + data.length);
                throw new Exception("请用:分开，如：192.168.1.1:yes。");
            }

            // 得到192.168.20-60.*:yes中的[192.168.20-60.*]部分
            String ruleIp = data[0];
            retValue = data[1].equalsIgnoreCase("yes") ? true : false;

            String[] ruleIpArray = StringUtils.split(ruleIp, '.');
            if (ruleIpArray.length != 4) {
                throw new Exception("IP部分错误！");
            }

            for (int i = 0; i < 4; i++) {
                boolean AA = ruleIpArray[i].contains("*");
                boolean BB = ruleIpArray[i].contains("-");
                boolean CC = ruleIpArray[i].contains("?");
                if ((AA && BB) || (AA && CC) || (BB && CC) || (AA && BB && CC)) {
                    throw new Exception("格式错误，*与-不能包含在同一个部分。");
                }
                // 没有包含 [*] 与 [-] 及 [?]
                else if (!AA && !BB && !CC) {
                    if (!Regex.isMatch(ruleIpArray[i], IP_BLOCK_REGEX)) {
                        throw new Exception("IP段错，应该在1~255之间：" + ruleIpArray[i] + "。");
                    } else {
                        if (ruleIpArray[i].equalsIgnoreCase(ipdata[i])) {
                            isFind = true;
                        } else {
                            isFind = false;
                            break;
                        }
                    }
                }
                // 包含 [*] 的
                else if (AA && !BB && !CC) {
                    if (!ruleIpArray[i].equalsIgnoreCase("*")) {
                        if (ruleIpArray[i].startsWith("*")
                                || !ruleIpArray[i].endsWith("*")
                                || ruleIpArray[i].contains("**")) {
                            throw new Exception("IP中的*部分：不能以*开头，不能有两个**，只能以*结尾。");
                        }
                    } else {
                        if (ipdata[i].startsWith(ruleIpArray[i].replace("*", ""))) {
                            isFind = true;
                        } else {
                            isFind = false;
                            break;
                        }
                    }
                }
                // 包含 [-] 的
                else if (BB && !AA && !CC) {
                    String[] temp = StringUtils.split(ruleIpArray[i], '-');
                    if (temp.length != 2) {
                        throw new Exception("IP段错误，如:23-50,在1~255之间。");
                    } else {
                        int[] nums = {Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};
                        if (nums[0] < 1 || nums[1] > 255) {
                            throw new Exception("IP段错误, 如:23-50,在1~255之间。");
                        } else {
                            int ipNum = Integer.parseInt(ipdata[i]);
                            if (ipNum >= nums[0] && ipNum <= nums[1]) {
                                isFind = true;
                            } else {
                                isFind = false;
                                break;
                            }
                        }
                    }
                }
                // 包含 [?] 的
                else if (CC && !AA & !BB) {
                    // 去掉问号后
                    String temp = ruleIpArray[i].replace("?", "");
                    if (!Regex.isMatch(temp, "^[0-9][0-9]?$") || temp.length() > 2) {
                        throw new Exception("IP段错误：" + ruleIpArray[i] + "。");
                    } else {
                        if (ruleIpArray[i].length() != ipdata[i].length()) {
                            isFind = false;
                            break;
                        } else {
                            String tempRegStr = "^" + ruleIpArray[i].replace("?", "([0-9])*") + "$";
                            if (Regex.isMatch(ipdata[i], tempRegStr)) {
                                isFind = true;
                            } else {
                                isFind = false;
                                break;
                            }
                        }
                    }
                } else {
                    isFind = false;
                    break;
                }
            }
            if (isFind) {
                return retValue;//IP规则中:后面的 yes/no 对应的 true false
            }
        }
        return false;
    }
}