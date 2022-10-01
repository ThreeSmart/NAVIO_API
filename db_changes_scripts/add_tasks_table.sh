until PGPASSWORD=threesmart psql -U threesmart -p 5433 -h localhost -d threesmart --no-password -c '\q'; do
  sleep 1
done

PGPASSWORD=threesmart psql -U threesmart -p 5433 -h localhost -d threesmart --no-password <<EOF
create table if not exists tasks
(
    id          bigserial primary key,
    name        text not null,
    owner_id    bigint not null,
    assignee_id bigint not null,
    start_date  bigint not null,
    end_date    bigint not null,
    status      text not null,
    priority    text not null,
    foreign key (owner_id) references users (id),
    foreign key (assignee_id) references users (id),
    CHECK (status in ('PENDING','COMPLETE')),
    CHECK (priority in ('LOW','MEDIUM', 'HIGH'))
);
EOF