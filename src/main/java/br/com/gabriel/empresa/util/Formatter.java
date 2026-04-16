package br.com.gabriel.empresa.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {

    private static final DateTimeFormatter DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final NumberFormat NUMBER =
            NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public static String date(java.time.LocalDate d) {
        return d.format(DATE);
    }

    public static String valor(BigDecimal v) {
        NUMBER.setMinimumFractionDigits(2);
        return NUMBER.format(v);
    }
}