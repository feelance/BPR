/* author: Florin-Leonard Bordei 
   1. Creating Orderlines table
 
 */

CREATE TABLE Orderlines (
                            OrderlineId int NOT NULL IDENTITY PRIMARY KEY ,
                            OrderNumber varchar(255),
                            ProductId int,
                            Quantity int,
                            Price decimal,
                            FOREIGN KEY  (OrderNumber) REFERENCES [Orders](OrderNumber),
                            FOREIGN KEY  (ProductId) REFERENCES [Products](Id) ON DELETE CASCADE
);
