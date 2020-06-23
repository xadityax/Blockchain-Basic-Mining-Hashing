# Blockchain-Basic-Mining-Hashing
Written in Java. Uses Java security mesagedigest to apply SHA-256 for hashing.
Four functions when you run the software - 

1:createBlock
2:verifyTransaction
3:mineBlock
4:viewUser

1. Initializes a new ArrayList representation of Blockchain consisting of blocks. Later adds blocks to it.
2. Calls isChainValid() in order to match the stored hashes to the calculated hashes.
3. Mines last added block with specified difficulty.
4. Take a peek at current Blockchain as a JSON.
