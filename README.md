# hiero-rare-evo

Hiero example for Rare Evo workshop

## How to run

The only requirement to run this example is to have Java 21 installed on your machine.
The project uses Maven as a build tool and brings the Maven Wrapper, so you don't need to have Maven installed globally.

### Build the project

To build the project, you can use the following command:

```bash
./mvnw verify
```

### Configure the environment

To run this example, you need to set up the environment variables.
You can do this by creating a `.env` file in the root directory of the project with the following content:

```
HIERO_NETWORK=hiero-solo-action
HIERO_ACCOUNT_ID=0.0.1001
HIERO_PRIVATE_KEY=3030020100300706052b8104000a0422042029deb0adc33ae0bfc5c802d2460897277c6628e52e21ea9de1a317042665900f
```

The `HIERO_NETWORK` variable specifies the network to use, which can be one of the following:
- `hiero-solo-action`: A solo based local testnetwork.
- `hedera-mainnet`: The Hedera mainnet network.
- `hedera-testnet`: The Hedera testnet network.

The `HIERO_ACCOUNT_ID` variable specifies the account ID to use, which should be a valid Hedera (ED25519) account ID.
The `HIERO_PRIVATE_KEY` variable specifies the private key to use, which should be a valid Hedera (ED25519) private key.

As a fast way to set up the environment variables you can create an account on the Hedera testnet and use the [Hedera Portal](https://portal.hedera.com/) to generate the account ID and private key.

### Run Spring Boot application

To run the Spring Boot application, you can use the following command:

```bash
./mvnw spring-boot:run
```

This will start the application on port 8080 by default.

### Run the plain Java application

Next to the Spring Boot application the repo contains a plain Java application that is using the Hiero SDK directly.
To run the plain Java application, you can use the following command:

```bash
./mvnw exec:java -Dexec.mainClass="com.hiero.rareevo.basic.DirectSdkUsage"
```

### Run the tests

To run the tests, you can use the following command:

```bash
./mvnw test
```
