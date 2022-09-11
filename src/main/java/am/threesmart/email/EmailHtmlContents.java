package am.threesmart.email;

import am.threesmart.enums.EmailType;
import am.threesmart.exceptions.NoSuchEmailTypeException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmailHtmlContents {

    private static final List<Map<String, String>> contents =
            List.of(
                    Map.of(
                            "RESET_PASSWORD",
                            "<html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "<head>\n" +
                                    "    <meta charset=\"UTF-8\">\n" +
                                    "    <title>Reset password</title>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<h2>To reset your password, just go by this link: <a href=\"%%host%%?token=%%token%%\">%%host%%</a></h2>\n" +
                                    "</body>\n" +
                                    "</html>"
                    )
            );

    public static String getContentByType(final EmailType emailType) {
        final Optional<String> content = contents.stream().findFirst().map(emailMap -> emailMap.get(emailType.toString()));

        if (content.isEmpty()) throw new NoSuchEmailTypeException();

        return content.get();
    }

    public static String getSubjectByType(final EmailType emailType) {
        return emailType.toString();
    }

}
