package com.hiero.rareevo.basic;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.TokenCreateTransaction;
import com.openelements.hiero.base.config.ConsensusNode;
import com.openelements.hiero.base.config.NetworkSettings;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Map;

public class DirectSdkUsage {

    public static void main(String[] args) throws Exception {
        final Dotenv dotenv = Dotenv.load();
        final AccountId operatorId = AccountId.fromString(dotenv.get("HIERO_ACCOUNT_ID"));
        final PrivateKey operatorKey = PrivateKey.fromString(dotenv.get("HIERO_PRIVATE_KEY"));
        final NetworkSettings networkSettings = NetworkSettings.forIdentifier(dotenv.get("HIERO_NETWORK"))
                .orElseThrow(() -> new IllegalStateException("Network settings not found"));
        final Client client = createClient(operatorId, operatorKey, networkSettings);

    }

    private static Client createClient(final AccountId operatorId, final PrivateKey operatorKey, final NetworkSettings networkSettings) {
        final ConsensusNode nodeToConnect = networkSettings.getConsensusNodes().iterator().next();
        final Map<String, AccountId> networkAccounts = Map.of(
                nodeToConnect.ip() + ":" + nodeToConnect.port(), AccountId.fromString(nodeToConnect.account())
        );
        final Client client = Client.forNetwork(networkAccounts);
        client.setOperator(operatorId, operatorKey);
        return client;
    }

}
