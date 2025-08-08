package com.hiero.rareevo;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.TokenId;
import com.openelements.hiero.base.HieroContext;
import com.openelements.hiero.base.HieroException;
import com.openelements.hiero.base.NftClient;
import com.openelements.hiero.base.mirrornode.NftRepository;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private final HieroContext hieroContext;

    private final NftRepository nftRepository;

    private final NftClient nftClient;

    private final TokenId nftType;

    @Autowired
    public ViewController(HieroContext hieroContext, NftRepository nftRepository, NftClient nftClient)
            throws HieroException {
        this.hieroContext = hieroContext;
        this.nftRepository = nftRepository;
        this.nftClient = nftClient;

        nftType = nftClient.createNftType("RareEvoToken", "RET");
    }

    @RequestMapping(value = "/mint", method = RequestMethod.POST)
    public String createNft(final Model model) throws Exception {
        String metadata = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        nftClient.mintNft(nftType, metadata.getBytes(StandardCharsets.UTF_8));
        updateModel(model);
        return "index";
    }

    @RequestMapping(value = "/burnall", method = RequestMethod.POST)
    public String burnAllNfts(final Model model) throws Exception {
        nftRepository.findByOwner(hieroContext.getOperatorAccount().accountId())
                .getData().forEach(nft -> {
                    try {
                        nftClient.burnNft(nft.tokenId(), nft.serial());
                    } catch (HieroException e) {
                        throw new RuntimeException("Can not burn NFT", e);
                    }
                });
        updateModel(model);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final Model model) throws Exception {
        updateModel(model);
        return "index";
    }

    private void updateModel(Model model) throws HieroException {
        AccountId owner = hieroContext.getOperatorAccount().accountId();
        List<NftModel> nfts = nftRepository.findByOwner(owner)
                .getData().stream()
                .map(nft -> new NftModel(nft.tokenId().toString(),
                        Long.toString(nft.serial()),
                        new String(nft.metadata(), StandardCharsets.UTF_8)))
                .toList();
        model.addAttribute("nfts", nfts);
    }

    public record NftModel(String name, String serial, String metadata) {
    }
}
