package hikma.reminder.util;

import static org.springframework.util.StringUtils.capitalize;

public class EnumHelper {
    public static String getPascalCaseString(java.lang.Enum enumerator){
        return capitalize(enumerator.name().toLowerCase());
    }
}
