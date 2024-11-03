using System.Threading.Tasks;
using Coravel.Invocable;
using Microsoft.Extensions.Logging;
using ShoeInc.Services;
using ShoeInc.Services.OrderService;

namespace ShoeInc.SalesGenerator.OnlineSalesGenerator
{
    // Author: Florin Bordei 
    // OnlineSalesGenerator is using Coravel, with the help of it the task can be scheduled 

    public class OnlineSalesGenerator : IInvocable
    {
        private readonly ILogger<OnlineSalesGenerator> Logger;
        private readonly OrderService orderService;

        public OnlineSalesGenerator(ILogger<OnlineSalesGenerator> Logger, OrderService orderService)
        {
            this.Logger = Logger;
            this.orderService = orderService;
        }  
        
        public async Task Invoke()
        {
           await orderService.RandomOrderGenerator();
            
        }
    }
}