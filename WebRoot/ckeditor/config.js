
/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/
CKEDITOR.editorConfig = function (config) {
	config.filebrowserBrowseUrl = "/qly/ckfinder/ckfinder.html";
	config.filebrowserImageBrowseUrl = "/qly/ckfinder/ckfinder.html?type=Images";
	config.filebrowserFlashBrowseUrl = "/qly/ckfinder/ckfinder.html?type=Flash";
	config.filebrowserUploadUrl = "/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files";
	config.filebrowserImageUploadUrl = "/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images";
	config.filebrowserFlashUploadUrl = "/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash";
	config.filebrowserWindowWidth = "1000";
	config.filebrowserWindowHeight = "700";
	config.language = "zh-cn";
	config.toolbar = 'Define'; //自定义工具栏 
	config.toolbar_Define = [
	       ['Source', '-', 'Preview', 'Print', 'Image'],
	       ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord'],
	       ['Bold', 'Italic', 'Underline', 'Strike'],
	       ['Styles', 'Format', 'Font', 'FontSize', 'TextColor', 'BGColor']
	    ];
};
/**
config.toolbar_Define = [
         ['Source', '-', 'Save', 'NewPage', 'Preview', '-', 'Templates'],
         ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Print', 'SpellChecker', 'Scayt','Image'],
         ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'],
         ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
         '/',
         ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
         ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],
         ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
         ['Link', 'Unlink', 'Anchor'],
         ['Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
         '/',
          ['Styles', 'Format', 'Font', 'FontSize'],
          ['TextColor', 'BGColor']
      ];
*/

