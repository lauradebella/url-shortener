create database urlshortener;

CREATE TABLE Url (
long_url varchar(20) NOT NULL DEFAULT '',
short_url varchar(20) NOT NULL DEFAULT '',
short_url_id varchar(20) NOT NULL DEFAULT '',
PRIMARY KEY (long_url)
);

CREATE INDEX long_url_on_url_table ON Url(long_url);
CREATE INDEX short_url_id_on_url_table ON Url(short_url_id);


CREATE TABLE Statistic (
dateTime varchar(20) NOT NULL DEFAULT '',
endpoint varchar(20) NOT NULL DEFAULT '',
was_successful boolean NOT NULL DEFAULT 0,
PRIMARY KEY (dateTime)
);


CREATE INDEX endpoint_on_statistic_table ON Statistic(endpoint);
CREATE INDEX was_successful_on_statistic_table ON Statistic(was_successful);
