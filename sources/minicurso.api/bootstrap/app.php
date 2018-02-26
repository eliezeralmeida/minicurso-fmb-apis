<?php

/**
 * Requisita o arquivo de autoload do composer
 * com o autoload é possível utilizar as classes
 * do projeto e do vendor sem que seja utilizado o
 * include de seus arquivos.
 */
require dirname(__DIR__) . "/src/defaults.php";

/**
 * Require composer autoload
 */
require DIR_ROOT . "/vendor/autoload.php";

/**
 * Cria uma instância do slim para fazer o controle de rotas
 */
$container = require DIR_ROOT . "/src/dependencies.php";
$app = new \Slim\App($container);

/*
 * Requisita o arquivo de rotas que está sendo gerenciado
 * pelo slim. Neste arquivo se faz o uso implícito da variável $app.
 */
require DIR_ROOT . "/src/routes.php";

/**
 * Inicia o controle de rotas do slim
 */
$app->run();
