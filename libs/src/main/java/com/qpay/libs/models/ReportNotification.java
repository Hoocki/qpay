package com.qpay.libs.models;

import lombok.Builder;

@Builder
public record ReportNotification (
        String filePath,

        String fileName,

        String email,

        String dateFrom,

        String dateTo
) {
}
