Create table wiederfinden.wf_item(id bigint primary key auto_increment,
                              name varchar(255),
                              location varchar(255),
                              filepath varchar(255),
                              awarded varchar(255),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                              );