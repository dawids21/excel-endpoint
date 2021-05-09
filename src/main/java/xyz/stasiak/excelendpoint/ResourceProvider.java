package xyz.stasiak.excelendpoint;

import org.springframework.core.io.ByteArrayResource;

import java.util.Optional;

interface ResourceProvider {

    Optional<ByteArrayResource> getResource();
}
