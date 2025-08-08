<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Hiero demo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="d-flex flex-column justify-content-center p-5 text-center">
  <form class="flex-fill">
    <button class="btn btn-outline-primary"
            type="submit" formaction="/mint" formmethod="post">Mint Nft
    </button>
    <button class="btn btn-outline-danger"
            type="submit" formaction="/burnall" formmethod="post">Burn all Nfts
    </button>
    <button class="btn btn-outline-primary"
            type="submit" formaction="/" formmethod="get">Refresh
    </button>
  </form>
  <table class="flex-fill">
    <tr>
      <th>Name</th>
      <th>Serial</th>
      <th>Metadata</th>
    </tr>
      <#list nfts as nft>
        <tr>
          <td>${nft.name()}</td>
          <td>${nft.serial()}</td>
          <td>${nft.metadata()}</td>
        </tr>
      </#list>
  </table>
</div>
</body>
</html>