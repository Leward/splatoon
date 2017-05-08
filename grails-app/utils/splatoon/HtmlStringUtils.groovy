package splatoon

import org.apache.commons.lang.StringUtils

import java.util.regex.Matcher
import java.util.regex.Pattern

class HtmlStringUtils {

    /**
     * @see <a href="http://stackoverflow.com/a/6289794/1566809">Snippet on StackOverflow</a>
     */
    public static String truncateHtml(String s, int l) {
        Pattern p = Pattern.compile("<[^>]+>([^<]*)");

        int i = 0;
        List<String> tags = new ArrayList<String>();

        Matcher m = p.matcher(s);
        while (m.find()) {
            if (m.start(0) - i >= l) {
                break;
            }

            String t = StringUtils.split(m.group(0), " \t\n\r\0\u000B>")[0].substring(1);
            if (t.charAt(0) != '/') {
                tags.add(t);
            } else if (tags.get(tags.size() - 1).equals(t.substring(1))) {
                tags.remove(tags.size() - 1);
            }
            i += m.start(1) - m.start(0);
        }

        Collections.reverse(tags);
        return s.substring(0, Math.min(s.length(), l + i)) +
                ((tags.size() > 0) ? "</" + StringUtils.join(tags, "></") + ">" : "") +
                ((s.length() > l) ? "\u2026" : "");

    }

}
