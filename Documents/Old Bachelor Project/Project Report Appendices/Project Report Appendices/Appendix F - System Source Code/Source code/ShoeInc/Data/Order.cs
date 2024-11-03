using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ShoeInc.Data
{
    // Author: Florin Bordei 
    public class Order
    {
       
        [Key][Column("OrderId")] public int OrderId { get; set; }
        public string OrderNumber { get; set; }
        public int UserId { get; set; }
        
        [NotMapped] 
        public List<Orderline> Orderlines { get; set; }
        public DateTime Date { get; set; }
        public decimal TotalPrice
        {
            get;
            set; 
        }
        
        public decimal GetTotalPrice()
        {
            decimal totalPrice = 0;
            Orderlines.ForEach(o => totalPrice += o.Price * o.Quantity);
            return totalPrice; 
        }
        
        
        
    }
}