using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookweb.beans
{
    public class Inventory
    {
        public String shelfno { get; set; }
        public List<InventoryBook> books { get; set; }
    }
}