// <auto-generated/>
#pragma warning disable 1591
#pragma warning disable 0414
#pragma warning disable 0649
#pragma warning disable 0169

namespace ShoeInc.Pages.Company_Manager
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.AspNetCore.Components.Web.Virtualization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using ShoeInc;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using ShoeInc.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using System.IO;

#line default
#line hidden
#nullable disable
#nullable restore
#line 12 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Radzen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 13 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/_Imports.razor"
using Radzen.Blazor;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/Company Manager/Management - Products.razor"
using ShoeInc.Data;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/Company Manager/Management - Products.razor"
using ShoeInc.Repositories.Products;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/Company Manager/Management - Products.razor"
using ShoeInc.Services.OrderService;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/Company Manager/Management - Products.razor"
using ShoeInc.Services.ProductService;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/ListOfProducts")]
    public partial class Management___Products : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
        }
        #pragma warning restore 1998
#nullable restore
#line 127 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/Company Manager/Management - Products.razor"
       

    RadzenDataGrid<Product> _productsGrid;
    private IList<Product> _products;
    private ProductRepository _productRepository; 

    protected override async Task OnInitializedAsync()
    {
         _productRepository = new ProductRepository(Context);
        _products =  (IList<Product>) await _productRepository.GetAllProductsAsync();

    }

 

    private void OnAddProduct()
    {
        NavigationManager.NavigateTo("/imageproduct");
    }
    
// when OnUpdateRow method is called the product is updated in the db and in the grid of the Radzen component 
    async void OnUpdateRow(Product product)
    {
        if (product.Discount != 0)
        {
      
            _productRepository.UpdateProduct(product);
            await ProductService.ApplyDiscount(product.ModelCode, product.Discount);
            NavigationManager.NavigateTo(NavigationManager.Uri, true);

        }
        else
        {
            _productRepository.UpdateProduct(product);
        }
        

    }

    // when DeleteRow method is called the product is deleted in the db and in the grid of the Radzen component 

    async Task  DeleteRow(Product product)
    {
        if (_products.Contains(product))
        {
            _productRepository.DeleteProduct(product);
            _products.Remove(product);
            await _productsGrid.Reload();
        }
        else
        {
            _productsGrid.CancelEditRow(product);
        }
        
    }

    async Task EditRow(Product product)
    {
        await _productsGrid.EditRow(product);

    }

    async Task  SaveRow(Product value)
    {

        await _productsGrid.UpdateRow(value);

    }

    private void CancelEdit(Product value)
    {
        _productsGrid.CancelEditRow(value);
    }

    // when RemoveDiscount method is called, the product returns to its original Price value 
    // logic resides in the ProductService 
    async void RemoveDiscount(decimal productDiscount, string productModelCode)
    {
        await ProductService.RemoveDiscount( productModelCode,  productDiscount);
        NavigationManager.NavigateTo(NavigationManager.Uri, true);

    }


#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private OrderService OrderService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ProductService ProductService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ShoeIncContext Context { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager NavigationManager { get; set; }
    }
}
#pragma warning restore 1591
