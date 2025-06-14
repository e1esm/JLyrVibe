package com.esm.storage.dto;

import java.io.InputStream;

public record GeneratedFile(InputStream content, String path) {
}
