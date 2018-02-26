<?php

namespace Api\Armazenamento;

class Storage
{
    /**
     * @var string
     */
    private static $file = DIR_ROOT."/tmp/storage.txt";

    /**
     * Storage constructor.
     */
    private function __construct()
    {
    }

    /**
     * @param array $input
     */
    public static function post(array $input): void
    {
        $read = self::read();
        $read[] = $input;

        self::write($read);
    }

    /**
     * @return array
     */
    public static function read(): array
    {
        if (file_exists(self::$file)) {
            $content = file_get_contents(self::$file);

            return unserialize($content);
        }

        return [];
    }

    /**
     * @param array $input
     */
    public static function write(array $input): void
    {
        file_put_contents(self::$file, serialize($input));
    }
}