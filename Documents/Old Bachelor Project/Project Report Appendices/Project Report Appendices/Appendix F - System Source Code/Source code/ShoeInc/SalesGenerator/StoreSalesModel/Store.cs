using System;
using System.Collections.Generic;

namespace ShoeInc.SalesGenerator.StoreSalesModel
{
    // Author: Florin Bordei 
    public class Store
    {
        // Store objects that will be attributed to SalesStore class 
        public string EmployeeName { get; set; }
        
        public int StoreId { get; set; }
        public string StoreName { get; set; }
        public string City { get; set; }
        public string Region { get; set;  }

        //method that returns a random store including an employee, storeName, city and region 
        public Store RandomStore()
        {
            IList<Store> stores = new List<Store>();

            Store storeAarhus = new Store
            {
                City = "Aarhus", Region = "Jylland", StoreName = "StoreAarhus", StoreId = 101
            };


            IList<string> employeesAarhus = new List<string>(); 
            employeesAarhus.Add("John");
            employeesAarhus.Add("Richie");
            employeesAarhus.Add("Dave");
            storeAarhus.EmployeeName = employeesAarhus[new Random().Next(0, 3)];

            Store storeOdense = new Store
            {
                City = "Odense", Region = "Fyn", StoreName = "Store Odense", StoreId = 102
            };



            IList<string> employeesOdense = new List<string>(); 
            employeesOdense.Add("Torben");
            employeesOdense.Add("Michael");
            employeesOdense.Add("Joe");
            storeOdense.EmployeeName = employeesOdense[new Random().Next(0, 3)];

            Store storeCopenhagen = new Store
            {
                City = "Copenhagen", Region = "Zealand", StoreName = "Store Copenhagen", StoreId = 102
            };



            IList<string> employeesCopenhagen = new List<string>(); 
            employeesCopenhagen.Add("Elie");
            employeesCopenhagen.Add("Nikos");
            employeesCopenhagen.Add("Isa");
            storeCopenhagen.EmployeeName = employeesCopenhagen[new Random().Next(0, 3)];
            
            stores.Add(storeAarhus);
            stores.Add(storeCopenhagen);
            stores.Add(storeOdense);

            return stores[new Random().Next(0, 3)];

        }
    }
}