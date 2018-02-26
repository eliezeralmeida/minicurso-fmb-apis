FROM php:7.1-apache

RUN apt-get update && apt-get install -y \
    libfreetype6-dev \
    libjpeg62-turbo-dev \
    libmcrypt-dev \
    libpng12-dev \
    zip \
    unzip \
    git

RUN docker-php-ext-install pdo pdo_mysql mbstring mysqli
RUN a2enmod rewrite

COPY ["composer.phar", "/bin/composer"]
COPY ["docker/apache2.conf", "/etc/apache2/apache2.conf"]
COPY ["docker/000-default.conf", "/etc/apache2/sites-available/"]
COPY ["docker/bootstrap.sh", "/bin/bootstrap"]

CMD ["bootstrap"]
