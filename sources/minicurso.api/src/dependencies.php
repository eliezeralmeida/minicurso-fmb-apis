<?php

/**
 * Container settings
 */
$settings = [
    "settings" => [
        "displayErrorDetails" => true,
        "responseChunkSize" => 8192,
    ],
];

$container = new Slim\Container($settings);

$container["notFoundHandler"] = function ($c) {
    return function ($req, $res) use ($c) {
        return $res->withStatus(404)
            ->withHeader('Content-type', 'text/html');
    };
};

$container["errorHandler"] = function ($c) {
    return function (\Slim\Http\Request $req, \Slim\Http\Response $res, Exception $ex) use ($c) {
        $error = [
            "error" => $ex->getMessage(),
            "trace" => $ex->getTrace(),
        ];

        return $res->withJson($error, 500);
    };
};


return $container;