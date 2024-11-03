using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Coravel;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using ShoeInc.SalesGenerator.OnlineSalesGenerator;
using ShoeInc.SalesGenerator.StoreSalesGenerator;


namespace ShoeInc
{
    public class Program
    {
        public static void Main(string[] args)
        {
               
            var host = CreateHostBuilder(args).Build();

         //   host.Services.UseScheduler(scheduler =>
         //   {
         //       var jobSchedule = scheduler.Schedule<StoreSalesGenerator>();
//
          //      jobSchedule.EveryMinute().Weekday().PreventOverlapping("Job");

         //   });
            
            
            
           // host.Services.UseScheduler(scheduler =>
           // {
           //     var jobSchedule = scheduler.Schedule<OnlineSalesGenerator>();

           //     jobSchedule.EveryMinute().PreventOverlapping("Job");

         //   });
            
            host.Run();
         
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder => { webBuilder.UseStartup<Startup>(); });
    }
}