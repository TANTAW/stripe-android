package com.stripe.android.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.stripe.android.model.Card;

import static com.stripe.android.model.Card.CardBrand;

/**
 * Utility class for common text-related operations on Stripe data coming from the server.
 */
public class StripeTextUtils {

    /**
     * Check to see if the input number has any of the given prefixes.
     *
     * @param number the number to test
     * @param prefixes the prefixes to test against
     * @return {@code true} if number begins with any of the input prefixes
     */
    public static boolean hasAnyPrefix(String number, String... prefixes) {
        if (number == null) {
            return false;
        }

        for (String prefix : prefixes) {
            if (number.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check to see whether the input string is a whole, positive number.
     *
     * @param value the input string to test
     * @return {@code true} if the input value consists entirely of integers
     */
    public static boolean isWholePositiveNumber(String value) {
        if (value == null) {
            return false;
        }

        return TextUtils.isDigitsOnly(value);
    }

    /**
     * Swap {@code null} for blank text values.
     *
     * @param value an input string that may or may not be entirely whitespace
     * @return {@code null} if the string is entirely whitespace, or the original value if not
     */
    public static String nullIfBlank(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value;
    }

    /**
     * A checker for whether or not the input value is entirely whitespace. This is slightly more
     * aggressive than {@link TextUtils#isEmpty(CharSequence)}, which only returns true for
     * {@code null} or {@code ""}.
     *
     * @param value a possibly blank input string value
     * @return {@code true} if and only if the value is all whitespace, {@code null}, or empty
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Converts an unchecked String value to a {@link CardBrand} or {@code null}.
     *
     * @param possibleCardType a String that might match a {@link CardBrand} or be empty.
     * @return {@code null} if the input is blank, else the appropriate {@link CardBrand}.
     */
    @Nullable
    @CardBrand
    public static String asCardBrand(@Nullable String possibleCardType) {
        if (isBlank(possibleCardType)) {
            return null;
        }

        if (Card.AMERICAN_EXPRESS.equals(possibleCardType)) {
            return Card.AMERICAN_EXPRESS;
        } else if (Card.MASTERCARD.equals(possibleCardType)) {
            return Card.MASTERCARD;
        } else if (Card.DINERS_CLUB.equals(possibleCardType)) {
            return Card.DINERS_CLUB;
        } else if (Card.DISCOVER.equals(possibleCardType)) {
            return Card.DISCOVER;
        } else if (Card.JCB.equals(possibleCardType)) {
            return Card.JCB;
        } else if (Card.VISA.equals(possibleCardType)) {
            return Card.VISA;
        } else {
            return Card.UNKNOWN;
        }
    }
}
