package com.qpay.transactionhistorymanager.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReportGeneratorUtils {
    public static final String QPAY_LOGO_IMAGE_PATH = "data/logo.png";

    public static final String REPORT_PATH = "data/reports/";

    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static final float LOGO_SCALE = 0.4F;

    public static final float TITLE_MARGIN_TOP = -75;

    public static final int TITLE_FONT_SIZE = 32;

    public static final float SUBTITLE_MARGIN_TOP = -10;

    public static final int SUBTITLE_FONT_SIZE = 14;

    public static final float CLIENT_NAME_MARGIN_TOP = 15;

    public static final float[] TABLE_COLUMN_WIDTHS = {50F, 150F, 100F, 150F, 250F};

    public static final String REPORT_PERIOD = "For period from %s to %s";

    public static final String FILE_NAME = "%s_%s.pdf";
}
