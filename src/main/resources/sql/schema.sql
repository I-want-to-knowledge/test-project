CREATE TABLE singer
(
    id         INT         NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(60) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    birth_date DATE,
    version    int         not null default 0,
    UNIQUE uq_singer_1 (first_name, last_name),
    PRIMARY KEY (id)
);

CREATE TABLE album
(
    id           INT          NOT NULL AUTO_INCREMENT,
    singer_id    INT          NOT NULL,
    title        VARCHAR(100) NOT NULL,
    release_date DATE,
    version      int          not null default 0,
    UNIQUE uq_singer_album_1 (singer_id, title),
    PRIMARY KEY (id),
    CONSTRAINT fk_album_singer FOREIGN KEY (singer_id) REFERENCES singer (id)
);

CREATE TABLE instrument
(
    instrument_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (instrument_id)
);

CREATE TABLE singer_instrument
(
    singer_id     INT         NOT NULL,
    instrument_id varchar(20) NOT NULL,
    PRIMARY KEY (singer_id, instrument_id),
    CONSTRAINT FK_singer_instrument_1 FOREIGN KEY (singer_id) REFERENCES singer (id) ON DELETE CASCADE,
    CONSTRAINT FK_singer_instrument_2 FOREIGN KEY (instrument_id) REFERENCES instrument (instrument_id)
);

CREATE TABLE singer_audit
(
    id                 INT         NOT NULL AUTO_INCREMENT,
    first_name         VARCHAR(60) NOT NULL,
    last_name          VARCHAR(40) NOT NULL,
    birth_date         DATE,
    version            int         not null default 0,
    created_by         VARCHAR(20),
    created_date       DATETIME,
    last_modified_by   VARCHAR(20),
    last_modified_date DATETIME,
    UNIQUE uq_singer_audit_1 (first_name, last_name),
    PRIMARY KEY (id)
);

CREATE TABLE singer_audit_h
(
    id                 INT         NOT NULL AUTO_INCREMENT,
    first_name         VARCHAR(60) NOT NULL,
    last_name          VARCHAR(40) NOT NULL,
    birth_date         DATE,
    version            int         not null default 0,
    created_by         VARCHAR(20),
    created_date       DATETIME,
    last_modified_by   VARCHAR(20),
    last_modified_date DATETIME,
    audit_revision INT NOT NULL COMMENT '历史记录的开始版本号',
    action_type INT COMMENT '操作类型，值： 0添加，1修改，2删除',
    audit_revision_end INT COMMENT '历史记录最终版',
    audit_revision_end_ts DATETIME COMMENT '最终更新的时间',
    UNIQUE uq_singer_audit_h_1 (first_name, last_name),
    PRIMARY KEY (id, audit_revision)
);

CREATE TABLE revinfo (
    revtstmp BIGINT NOT NULL COMMENT '创建版本时时间戳',
    rev INT NOT NULL AUTO_INCREMENT COMMENT '存储每个版本号',
    PRIMARY KEY (rev, revtstmp)
);