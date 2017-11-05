using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;


namespace bookweb.Utils
{
    public class MyJsonResult : JsonResult
    {


        public override void ExecuteResult(ControllerContext context)
        {
            //base.ExecuteResult(context);

            if (context == null)
            {
                throw new ArgumentNullException("context");
            }

            //if ((this.JsonRequestBehavior == JsonRequestBehavior.DenyGet) && string.Equals(context.HttpContext.Request.HttpMethod, "GET", StringComparison.OrdinalIgnoreCase))
            //{
            //    throw new InvalidOperationException(MvcResources.JsonRequest_GetNotAllowed);
            //}

            HttpResponseBase response = context.HttpContext.Response;
            if (!string.IsNullOrEmpty(this.ContentType))
            {
                response.ContentType = this.ContentType;
            }
            else
            {
                response.ContentType = "application/json";
            }
            if (this.ContentEncoding != null)
            {
                response.ContentEncoding = this.ContentEncoding;
            }
            if (this.Data != null)
            {
                //JavaScriptSerializer serializer = new JavaScriptSerializer();
                //response.Write(serializer.Serialize(this.Data));

                response.Write(Json.ToJson(this.Data));

            }

        }
    }
}