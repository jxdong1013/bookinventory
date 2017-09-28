namespace bookweb.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class rename : DbMigration
    {
        public override void Up()
        {
            RenameTable(name: "dbo.AspNetRoles", newName: "t_roles");
            RenameTable(name: "dbo.AspNetUserRoles", newName: "t_userroles");
            RenameTable(name: "dbo.AspNetUserClaims", newName: "t_userclaims");
            RenameTable(name: "dbo.AspNetUserLogins", newName: "t_userlogins");
            DropColumn("dbo.t_roles", "Discriminator");
        }
        
        public override void Down()
        {
            AddColumn("dbo.t_roles", "Discriminator", c => c.String(nullable: false, maxLength: 128));
            RenameTable(name: "dbo.t_userlogins", newName: "AspNetUserLogins");
            RenameTable(name: "dbo.t_userclaims", newName: "AspNetUserClaims");
            RenameTable(name: "dbo.t_userroles", newName: "AspNetUserRoles");
            RenameTable(name: "dbo.t_roles", newName: "AspNetRoles");
        }
    }
}
