/* author: Florin-Leonard Bordei 
   1. Creating Orders table
   */

CREATE TABLE Orders (
                        OrderId int NOT NULL IDENTITY PRIMARY KEY ,
                        OrderNumber varchar(255) UNIQUE,
                        UserId int,
                        Date DATE,
                        TotalPrice decimal ,
                        FOREIGN KEY  (UserId) REFERENCES [User](ID) ON DELETE CASCADE
);
