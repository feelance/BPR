/* author: Florin-Leonard Bordei 
   1. Creating Product table
   2. Adding product values in Product table 
 */

CREATE TABLE Products (
                          Id int NOT NULL IDENTITY PRIMARY KEY ,
                          Brand varchar(255),
                          Model varchar(255),
                          ModelCode varchar(255),
                          Price double precision ,
                          Discount double precision,
                          Description varchar(255),
                          Image varbinary(MAX),
                          ImageUrl varchar(MAX),
                          Stocklevel int,
);
