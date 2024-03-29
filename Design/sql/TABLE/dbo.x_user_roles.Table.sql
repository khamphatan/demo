SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[x_user_roles](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[x_user_roles] ([user_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[x_user_roles] ([user_id], [role_id]) VALUES (1, 2)
ALTER TABLE [dbo].[x_user_roles]  WITH CHECK ADD  CONSTRAINT [FKqq4uy7t20r1r90sogyradx5kk] FOREIGN KEY([user_id])
REFERENCES [dbo].[x_user] ([id])
GO
ALTER TABLE [dbo].[x_user_roles] CHECK CONSTRAINT [FKqq4uy7t20r1r90sogyradx5kk]
GO
ALTER TABLE [dbo].[x_user_roles]  WITH CHECK ADD  CONSTRAINT [FKr7995e7n87xdhy2atwaa73yk7] FOREIGN KEY([role_id])
REFERENCES [dbo].[x_roles] ([id])
GO
ALTER TABLE [dbo].[x_user_roles] CHECK CONSTRAINT [FKr7995e7n87xdhy2atwaa73yk7]
GO
