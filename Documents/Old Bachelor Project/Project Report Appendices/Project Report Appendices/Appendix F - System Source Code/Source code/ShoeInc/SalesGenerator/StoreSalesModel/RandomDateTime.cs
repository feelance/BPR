using System;

namespace ShoeInc.SalesGenerator.StoreSalesModel
{
    // Author: Florin Bordei 
    public class RandomDateTime
    {
        // the purpose of RandomDateTime is to create DateTime objects with random Dates 
        // starting from 2020/01/01 to current date 
        DateTime start;
        Random gen;
        int range;
        
        public RandomDateTime()
        {
            start = new DateTime(2020, 1, 1);
            gen = new Random();
            range = (DateTime.Today - start).Days;
        }
 
        public DateTime Next()
        {
            return start.AddDays(gen.Next(range)).AddHours(gen.Next(0,24)).AddMinutes(gen.Next(0,60)).AddSeconds(gen.Next(0,60));
        }
    }
}