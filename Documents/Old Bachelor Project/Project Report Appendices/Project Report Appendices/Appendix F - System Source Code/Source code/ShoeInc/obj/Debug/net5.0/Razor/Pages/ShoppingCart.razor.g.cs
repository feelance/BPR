#pragma checksum "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "023f0cc0dff74af07bb0f6bed5f8379549bc62ef"
// <auto-generated/>
#pragma warning disable 1591
namespace ShoeInc.Pages
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
#line 2 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using ShoeInc.Data.ShoppingCart;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using ShoeInc.Data;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using System.Security.Claims;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using ShoeInc.Areas.Identity;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using ShoeInc.Repositories.Orders;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
using ShoeInc.Services.OrderService;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/MyShoppingCart")]
    public partial class ShoppingCart : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
#nullable restore
#line 21 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
 if (CartItems != null && CartItems.Any())
{
    

#line default
#line hidden
#nullable disable
            __builder.OpenElement(0, "div");
            __builder.AddAttribute(1, "class", "panel panel-info");
            __builder.AddMarkupContent(2, "<div class=\"panel-heading\"><h3 class=\"panel-title\">Order Summary</h3></div>\n        ");
            __builder.OpenElement(3, "div");
            __builder.AddAttribute(4, "class", "panel-body");
            __builder.AddMarkupContent(5, "<div id=\"update-message\"></div>\n            ");
            __builder.OpenElement(6, "table");
            __builder.AddAttribute(7, "class", "table table-striped table-hover ");
            __builder.AddMarkupContent(8, @"<thead><tr><th>
                            Item Name
                        </th>
                        <th>
                            Price (each)
                        </th>
                        <th>
                            Quantity
                        </th>
                        <th>
                            Total Price
                        </th>
                        <th></th></tr></thead>");
#nullable restore
#line 49 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                 foreach (var item in CartItems)
                {

#line default
#line hidden
#nullable disable
            __builder.OpenElement(9, "tr");
            __builder.OpenElement(10, "td");
            __builder.AddContent(11, 
#nullable restore
#line 53 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                             item.Model

#line default
#line hidden
#nullable disable
            );
            __builder.CloseElement();
            __builder.AddMarkupContent(12, "\n                        ");
            __builder.OpenElement(13, "td");
            __builder.AddContent(14, 
#nullable restore
#line 56 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                             item.Price.ToString("N2")

#line default
#line hidden
#nullable disable
            );
            __builder.AddMarkupContent(15, " $\n                        ");
            __builder.CloseElement();
            __builder.AddMarkupContent(16, "\n                        ");
            __builder.OpenElement(17, "td");
            __builder.AddContent(18, 
#nullable restore
#line 59 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                             item.Quantity

#line default
#line hidden
#nullable disable
            );
            __builder.CloseElement();
            __builder.AddMarkupContent(19, "\n                       \n                        ");
            __builder.OpenElement(20, "td");
            __builder.OpenElement(21, "button");
            __builder.AddAttribute(22, "class", "btn btn-primary");
            __builder.AddAttribute(23, "id", "button");
            __builder.AddAttribute(24, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 63 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                                                                       (()=> removeFromCart(item.Id))

#line default
#line hidden
#nullable disable
            ));
            __builder.AddContent(25, "Remove From Cart");
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.AddMarkupContent(26, "\n                        ");
            __builder.OpenElement(27, "div");
            __builder.AddAttribute(28, "class", "media-img-wrapper mr-2");
            __builder.OpenElement(29, "a");
            __builder.AddAttribute(30, "href", 
#nullable restore
#line 66 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                                  item.ModelCode

#line default
#line hidden
#nullable disable
            );
            __builder.OpenElement(31, "img");
            __builder.AddAttribute(32, "class", "media-img");
            __builder.AddAttribute(33, "src", 
#nullable restore
#line 67 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                                                         item.ImageUrl

#line default
#line hidden
#nullable disable
            );
            __builder.AddAttribute(34, "alt", 
#nullable restore
#line 67 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                                                                              item.Model

#line default
#line hidden
#nullable disable
            );
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
#nullable restore
#line 71 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                }

#line default
#line hidden
#nullable disable
            __builder.OpenElement(35, "tr");
            __builder.AddAttribute(36, "class", "info");
            __builder.AddMarkupContent(37, "<td></td>\n                    <td></td>\n                    <td></td>\n                    ");
            __builder.OpenElement(38, "td");
            __builder.AddAttribute(39, "id", "cart-total");
            __builder.AddMarkupContent(40, "\n                        Total : ");
            __builder.AddContent(41, 
#nullable restore
#line 77 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                 CartTotal.ToString("N2")

#line default
#line hidden
#nullable disable
            );
            __builder.AddContent(42, " $    ");
            __builder.OpenElement(43, "button");
            __builder.AddAttribute(44, "class", "btn btn-primary");
            __builder.AddAttribute(45, "id", "button");
            __builder.AddAttribute(46, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 77 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
                                                                                                                      (()=> createOrder())

#line default
#line hidden
#nullable disable
            ));
            __builder.AddContent(47, "Confirm Order");
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
            __builder.CloseElement();
#nullable restore
#line 83 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
}
else
{

#line default
#line hidden
#nullable disable
            __builder.AddMarkupContent(48, "<div class=\"custom-bg\"><p>There are no items in your cart currently. Please shop for something and add items..</p></div>");
#nullable restore
#line 89 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
}

#line default
#line hidden
#nullable disable
        }
        #pragma warning restore 1998
#nullable restore
#line 92 "/Users/florinbordei/Documents/GitHub/shoeinc/Application/ShoeInc/Pages/ShoppingCart.razor"
 
    
    
    public ICollection<Product> CartItems { get; set; }
    
    public decimal CartTotal => CartItems.Sum(c => c.Price * c.Quantity);

    protected override async Task OnInitializedAsync()
    {
        var principal = await _authenticationStateProvider.GetAuthenticationStateAsync();

        
        
        if (_signInManager.IsSignedIn(principal.User))
        {
            Console.WriteLine("Is signed in");
            CartItems =  _shoppingCartService.getProducts();
        }
        else
        {
            CartItems =  _shoppingCartService.getProducts();
            Console.WriteLine("Got shopping cart");
        }
         

    }

    protected async Task removeFromCart(int id)
    {
        _shoppingCartService.RemoveFromCart(id);
        Product product = new Product();
        product.Id = id;

        foreach (var c in CartItems)
        {
            if (c.Id == id)
            {
                CartItems.Remove(c);
            }
            break;
       
    }

    // appState.setCartCount(CartItems.Count);
    // StateHasChanged();
    }
    private void createOrder()
    {
        _shoppingCartService.CreateOrder(CartItems);
        NavManager.NavigateTo("/");

    }
   
   

#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ShoeInc.Services.ShoppingCartService _shoppingCartService { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager NavManager { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private AuthenticationStateProvider _authenticationStateProvider { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ShoeInc.Data.ShoppingCart.Cart cart { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private Microsoft.AspNetCore.Identity.UserManager<ShoeInc.Areas.Identity.Data.ShoeIncUser> _userManager { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private Microsoft.AspNetCore.Identity.SignInManager<ShoeInc.Areas.Identity.Data.ShoeIncUser> _signInManager { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private AppState appState { get; set; }
    }
}
#pragma warning restore 1591