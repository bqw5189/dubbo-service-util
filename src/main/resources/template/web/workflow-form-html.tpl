<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title></title>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="stylesheet" type="text/css" href="../../../appFramework/build/icons.css"/>
    <link rel="stylesheet" type="text/css" href="../../../appFramework/build/af.ui.css"/>
    <link rel="stylesheet" type="text/css" href="../backLogs.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/common.css"/>
    <script src="../../js/jquery-1.8.3.min.js"></script>
    <script src="../../js/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="../../../appFramework/build/appframework.ui.min.js"></script>
    <script type="text/javascript" src="../../myjs/common.js"></script>
    <script type="text/javascript" src="../backlog.js"></script>
    <script type="text/javascript" src="../initUtils.js"></script>
    <script type="text/javascript" src="../buildUtils.js"></script>
    <script type="text/javascript" src="../submitUtils.js"></script>
    <script type="text/javascript" src="detail.js"></script>
</head>
<body>
<div class="view" style="background: #000;">
    <div class="pages">
        <div class="panel panel-padding" align="left">
        <#foreach from=$panels item=panel#>
        <!--  <# $panel.title #> -->
        <div class="input-group input-group-padding">
            <div class="icon info" onclick="">
                <span field-label="<#$panel.id#>"></span>
            </div>
            <#if $panel.type == "value" #>
            <div id="<#$panel.id#>">
                <table>
                    <#foreach from=$panel.elements item=element#><tr>
                        <td width="34%"><span field-label="<#$element.id#>"></span></td>
                        <td id="<#$element.id#>"></td>
                    </tr><#/foreach#>
                </table>
             </div>
             <#/if#>
             <#if $panel.type != "value" #><#foreach from=$panel.elements item=element#><div id="<#$element.id#>" class="overflow-x">
             </div><#/foreach#><#/if#>
        </div><#/foreach#>

            <!-- 审查审批历史  -->
            <div class="input-group input-group-padding">
                <div class="icon magnifier" onclick="">
                    <span field-label="Record"></span>
                </div>
                <div id="Record" class="overflow-x">
                </div>
            </div>
            <!-- 审查审批意见  -->
            <div class="input-group input-group-padding">
                <div class="icon message" onclick="">
                    <span field-label="Review_approval_collaborative"></span><span field-label="Review_approval"></span>
                </div>
                <div id="Review_approval">
                    <textarea rows="3" field-label="Review_approval_reason" id="Review_approval_reason"></textarea>
                    <select id="default_reason"></select>
                    <div class="div-padding">
                        <a class="button " id="coMidReview" ><span field-label="CoMidReview"></span></a>
                        <a class="button " id="submit" ><span field-label="Submit"></span></a>
                        <a class="button " id="reject"><span field-label="Reject"></span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>