## Running the sample code

1. Start a first node with the Cart service:

    ```
    sbt.bat "-Dconfig.resource=local1.conf run"
    ```

    Exercise the Cart service API
    ```
    grpcurl -d '{"cartId":"cart1", "itemId":"t-shirt", "quantity":2}' -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem
    ```

    Note: the `grpcurl` needs to be executed in BASH terminal. The other terminals (PowerShell or CMD) fails with errors like that: `Error invoking method "shoppingcart.ShoppingCartService.AddItem": error getting request data: invalid character 'c' looking for beginning of object key string`. The reason of that will be in the specification of the request streing (the value of the argument `-d` - there are issues with the apostrophes / quotes)

2. (Optional) Start another node with different ports:

    ```
    sbt -Dconfig.resource=local2.conf run
    ```

3. Check for service readiness

    ```
    curl http://localhost:9101/ready
    ```
