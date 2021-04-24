# NaughtyFilter

Filter naughty words from chat and commands. Naughty words in chat
will be replaced with `***`. Naughty words in commands will cause the
command execution to be denied, along with a feedback message.

## Configuration

The default `config.yml`:
```yaml
# List of commands to filter. Must all be lowercase!
FilterCommands: [nick, nickname]
FilterChat: true
Feedback: '&cNaughty words are not allowed!'
```

Naughty words:
- `naughty.txt` Contains words to be denied in commands
- `chat.txt` Contains words to be filtered from chat

The naughty word lists will be copied into the plugin folder if they
don't exist yet. Each line is one naughty word. Commands listed in
`FilterCommands` will be blocked if they contain any of the words. If
`FilterChat` is enabled, any naughty word in chat will be replaced
with asterisks.

## Permissions

- `naughtyfilter.naughtyfilter` Use the admin command
- `naughtyfilter.override` Override any word filter

## Commands

- `/naughtywords reload` Reload the configuration and word list