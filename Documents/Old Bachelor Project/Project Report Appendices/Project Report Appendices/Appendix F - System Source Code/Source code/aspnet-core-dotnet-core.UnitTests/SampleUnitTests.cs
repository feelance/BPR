
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Bunit;
using MockQueryable.Moq;
using Moq;
using Moq.Language;
using ShoeInc.Data;
using ShoeInc.Repositories;
using ShoeInc.Repositories.Products;
using Xunit;
using Assert = Microsoft.VisualStudio.TestTools.UnitTesting.Assert;

namespace aspnet_core_dotnet_core.UnitTests
{
    [TestClass]
    public class SampleUnitTests
    {
        [TestMethod]
        public void IndexPageTest()
        {
            Assert.AreEqual("Index", actual: "Index");
        }

        [TestMethod]
        public void FetchPageTest()
        {
            Assert.AreEqual("FetchData", "FetchData");
        }
        
     
    }
}
