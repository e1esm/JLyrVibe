CREATE TABLE IF NOT EXISTS files(
    id uuid primary key,
    file_type text not null,
    s3_path text not null,
    size int not null,
    created_at timestamp without time zone not null,
    deleted_at timestamp without time zone
);