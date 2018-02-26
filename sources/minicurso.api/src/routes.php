<?php

use Api\Armazenamento\Storage;
use Slim\Http\Request as Request;
use Slim\Http\Response as Response;

$app->get("/", function (Request $req, Response $res) {
    $corpo = ["esta-funcionando" => "sim"];

    return $res->withJson($corpo, 200);
});

$app->get("/pessoas", function (Request $req, Response $res) {
    $content = Storage::read();

    return $res->withJson($content, 200);
});

$app->post("/pessoas", function (Request $req, Response $res) {
    $input = $req->getParsedBody();

    $pessoa = [
        "_id" => uniqid(),
        "nome" => $input["nome"],
        "email" => $input["email"],
    ];

    Storage::post($pessoa);

    return $res->withJson($pessoa, 200);
});

$app->put("/pessoas/{id}", function (Request $req, Response $res, array $args) {
    $input = $req->getParsedBody();
    $rows = Storage::read();

    foreach ($rows as &$row) {
        if ($row["_id"] != $args["id"]) {
            continue;
        }

        $row["nome"] = $input["nome"];
        $row["email"] = $input["email"];

        Storage::write($rows);

        return $res->withJson($row, 200);
    }

    return $res->withJson([], 400);
});

$app->delete("/pessoas/{id}", function (Request $req, Response $res, array $args) {
    $rows = Storage::read();
    $newRows = [];

    foreach ($rows as $key => $row) {
        if ($row["_id"] != $args["id"]) {
            $newRows[] = $row;
        }
    }

    Storage::write($newRows);

    return $res->withJson([], 200);
});