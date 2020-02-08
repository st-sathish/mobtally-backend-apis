
create database "tally" with owner "postgres" encoding 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8' TEMPLATE template0;

grant all privileges on database tally to postgres;

# Login postgres for tally database
sudo -u postgres psql tally