/* 点击按钮弹出模态框 */
$(function () {
    $('.batch').click(function () {
        // 灰背景遮挡效果
        $('.zhezhao').css('display', 'block');
        $('#batchProv').fadeIn();
    });

    /* 点击 确定 */
    $('#upload').click(function () {
        //获取文件输入值（文件名）
        var uploadVal = $('#fileId').val();
        //获取文件后缀名
        var suffixSign = uploadVal.lastIndexOf(".");
        var fileType = uploadVal.substring(suffixSign, uploadVal.length);

        //1.判断文件是否为空
        if (uploadVal == null || uploadVal == "") {
            alert("请选择文件！");
        }
        //2.判断文件类型是否合法
        else if (fileType.toLowerCase() != ".xls" && fileType.toLowerCase() != ".xlsx") {
            alert("仅支持后缀名为'.xls'和'.xlsx'的 Excel 文件！\n请重新选择！");
        }
        /* 成功导入用户 */
        else {
            $('.zhezhao').css('display', 'none');
            $('#batchProv').fadeOut();
            $("#batchForm").submit();
            $('.respMsg').html('正在添加中...').addClass('respMsg-success').show().delay(10000).fadeOut();//停留显示10秒
        }
    });

    /* 点击 取消 */
    $('#cancel').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#batchProv').fadeOut();
    });
});
