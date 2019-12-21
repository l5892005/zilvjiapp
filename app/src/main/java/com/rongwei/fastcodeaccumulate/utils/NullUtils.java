package com.rongwei.fastcodeaccumulate.utils;

import android.widget.EditText;

import io.reactivex.annotations.Nullable;

/**
 * Created by maoqi on 2017/7/18.
 */

public class NullUtils {

    private NullUtils() {
    }

    private static String format(String template, @Nullable Object... args) {
        template = String.valueOf(template); // null -> "null"

        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template, templateStart, placeholderStart);
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template, templateStart, template.length());

        // if we run out of placeholders, append the extra args in square braces
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }

        return builder.toString();
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    public static <T> T checkNotNull(
            T reference, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (reference == null) {
            // If either of these parameters is null, the right thing happens anyway
            throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }


    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }


    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }


    public static <T> T checkNotNull(
            T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj,
            @Nullable String errorMessageTemplate,
            @Nullable Object p1,
            @Nullable Object p2,
            @Nullable Object p3) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2, p3));
        }
        return obj;
    }

    public static <T> T checkNotNull(
            T obj,
            @Nullable String errorMessageTemplate,
            @Nullable Object p1,
            @Nullable Object p2,
            @Nullable Object p3,
            @Nullable Object p4) {
        if (obj == null) {
            throw new NullPointerException(format(errorMessageTemplate, p1, p2, p3, p4));
        }
        return obj;
    }


    public static int checkNotNullEmpty( EditText view){
        String trim = view.getText().toString().trim();
        if (trim==null || trim.length()==0){
            return 0;
        }else{
            return trim.length();
        }
    }
}
