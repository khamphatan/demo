SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		PORNCHAI.K
-- Description:	Get user data
-- =============================================
CREATE PROCEDURE dbo.SP_GET_USER_INFO
--ALTER PROCEDURE [dbo].[SP_GET_USER_INFO]
	@PRM_REFERENCE_CODE VARCHAR(20)
AS
BEGIN
	SET NOCOUNT ON;

	PRINT 'CALL :: SP_GET_USER_INFO';
	PRINT '- @PRM_REFERENCE_CODE = ' + @PRM_REFERENCE_CODE;

	/* SET PARAMETER */

	/* GET DATA */
	WITH RESULT AS
	(
		SELECT us.REFERENCE_CODE, us.USERNAME, us.[STATUS]
			, us.TITLE_CODE
			, us.FIRST_NAME
			, us.LAST_NAME
			, us.FIRST_NAME_ENG
			, us.LAST_NAME_ENG
			, us.CITIZEN_ID
			, us.[ADDRESS]
			, us.EMAIL
			, us.GENDER_CODE
			, us.MOBILE_NUMBER
			, us.PHONE_NUMBER
			, us.SALARY
			, us.MEMBER_TYPE
		FROM dbo.X_USER AS us WITH (NOLOCK)
		WHERE us.REFERENCE_CODE = @PRM_REFERENCE_CODE
	)

	/* RETURN DATA */
	SELECT r.*
		--> Custom Field : TITLE_NAME
		, (select top 1 lov.MST_LOV_NAME_TH from dbo.MST_LOV as lov with (nolock) where lov.MST_LOV_ID = r.TITLE_CODE) AS TITLE_NAME
		, (select top 1 lov.MST_LOV_NAME_ENG from dbo.MST_LOV as lov with (nolock) where lov.MST_LOV_ID = r.TITLE_CODE) AS TITLE_NAME_ENG

		--> Custom Field : GENDER
		, (select top 1 lov.MST_LOV_NAME_TH from dbo.MST_LOV as lov with (nolock) where lov.MST_LOV_ID = r.GENDER_CODE) AS GENDER_NAME
		, (select top 1 lov.MST_LOV_NAME_ENG from dbo.MST_LOV as lov with (nolock) where lov.MST_LOV_ID = r.GENDER_CODE) AS GENDER_NAME_ENG
	FROM RESULT AS r
	
END