package net.noyark.www.utils.command

import net.noyark.www.utils.Message


class Help constructor(var map:Map<String,CommandBase>): CommandBase{


    override fun execute(args: Array<out String>?):Any {
        for(entry in map){
            var cmd = entry.key
            val cmdobj = entry.value
            var usages =  cmdobj.usage()
            Message.info(cmd)
            for(usage in usages){
                Message.info(usage)
            }
        }
        return ""
    }

    override fun usage(): Array<String> {
        return arrayOf("用于帮助")
    }


}