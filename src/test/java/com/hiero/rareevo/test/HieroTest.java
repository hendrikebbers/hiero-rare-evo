package com.hiero.rareevo.test;

import com.hedera.hashgraph.sdk.FileId;
import com.openelements.hiero.base.FileClient;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HieroTest {

    @Autowired
    private FileClient fileClient;

    @Test
    public void testFileClient() throws Exception {
        String content = "Hello, Rare Evo!";

        // Upload a file
        final FileId fileId = fileClient.createFile(content.getBytes(Charset.defaultCharset()));

        // Download the file
        byte[] downloadedContent = fileClient.readFile(fileId);

        // Verify the content
        Assertions.assertEquals(content, new String(downloadedContent, Charset.defaultCharset()));
    }

}
