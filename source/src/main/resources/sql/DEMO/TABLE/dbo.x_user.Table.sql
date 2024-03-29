SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[x_user](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NULL,
	[citizen_id] [varchar](15) NULL,
	[email] [varchar](100) NULL,
	[encoded_password] [varchar](255) NULL,
	[first_name] [varchar](100) NULL,
	[first_name_eng] [varchar](100) NULL,
	[gender_code] [varchar](20) NULL,
	[last_name] [varchar](100) NULL,
	[last_name_eng] [varchar](100) NULL,
	[member_type] [varchar](20) NULL,
	[mobile_number] [varchar](20) NULL,
	[phone_number] [varchar](20) NULL,
	[reference_code] [varchar](20) NULL,
	[salary] [float] NULL,
	[status] [varchar](20) NULL,
	[title_code] [varchar](20) NULL,
	[username] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[x_user] ON 

INSERT [dbo].[x_user] ([id], [address], [citizen_id], [email], [encoded_password], [first_name], [first_name_eng], [gender_code], [last_name], [last_name_eng], [member_type], [mobile_number], [phone_number], [reference_code], [salary], [status], [title_code], [username]) VALUES (1, N'test 1234', N'1254687925226', N'khamphatan@gmail.com', N'$2a$10$h6pICLEG5gRNx6lUH7uVKuAfzSjKyFZGOoQ3m6y85Q2Vyvet36CzS', N'พรชัย', N'PORNCHAi', N'M', N'คำภาตัน', N'KHAMPHATAN', N'PLATINUM', N'0898878999', N'', N'201909158999', 1000000, N'ACTIVE', N'MR', N'pornchai')
SET IDENTITY_INSERT [dbo].[x_user] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_21xyxcsu2cexbialpnwo0ka21]    Script Date: 15 ก.ย. 2562 12:39:07 ******/
ALTER TABLE [dbo].[x_user] ADD  CONSTRAINT [UK_21xyxcsu2cexbialpnwo0ka21] UNIQUE NONCLUSTERED 
(
	[gender_code] ASC,
	[member_type] ASC,
	[status] ASC,
	[title_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKf4urxc4e9c3jfxmw6lb0c5x7l]    Script Date: 15 ก.ย. 2562 12:39:07 ******/
ALTER TABLE [dbo].[x_user] ADD  CONSTRAINT [UKf4urxc4e9c3jfxmw6lb0c5x7l] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKqa8ligjf7rvk5kb87w7yvhxsw]    Script Date: 15 ก.ย. 2562 12:39:07 ******/
ALTER TABLE [dbo].[x_user] ADD  CONSTRAINT [UKqa8ligjf7rvk5kb87w7yvhxsw] UNIQUE NONCLUSTERED 
(
	[reference_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
