# NaughtyFilter

Filter naughty words from chat and commands.

## Configuration

The default `config.yml`:
```yaml
# List of commands to filter. Must all be lowercase!
FilterCommands: [nick, nickname]
FilterChat: true
Feedback: '&cNaughty words are not allowed!'
```

The `naughty.txt` will be copied into the plugin folder if it doesn't
exist yet. Each line is one naughty word. Commands listed in
`FilterCommands` will be blocked if they contain any of the words. If
`FilterChat` is enabled, any naughty word in chat will be replaced
with asterisks.

## Permissions

- `naughtyfilter.naughtyfilter` Use the admin command
- `naughtyfilter.override` Override any word filter

## Commands

- `/naughtywords reload` Reload the configuration and word list