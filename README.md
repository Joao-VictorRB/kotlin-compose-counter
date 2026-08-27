# Contador — App Android com Jetpack Compose

**Nome:** _(preencha aqui com o seu nome antes de entregar)_

## Como o estado funciona no app

O valor do contador é guardado dentro da `ContadorViewModel`, em uma propriedade
declarada como `var contador by mutableStateOf(...)`. Isso transforma
`contador` em um "estado observável" do Compose: quando `incrementar()`,
`decrementar()` ou `zerar()` alteram esse valor, o Compose sabe exatamente
quais funções `@Composable` leram essa variável (no caso, o `Text` dentro de
`ContadorScreen`) e agenda a recomposição **apenas delas**, automaticamente.
É por isso que nunca chamamos algo como `atualizarTela()` manualmente: o
próprio ato de ler um `State` dentro de um Composable já registra essa
"inscrição", e escrever nele dispara a atualização. A `MainActivity` só monta
o tema e delega tudo para `ContadorRoute`/`ContadorScreen`, que ficam em
arquivos separados (`ContadorScreen.kt` e `ContadorViewModel.kt`).

## Desafios opcionais implementados

- **D1** — O botão "-1" fica desabilitado (`enabled = contador > 0`) quando o
  contador chega a zero, impedindo valores negativos.
- **D2** — Botão "Zerar" adicionado como `TextButton`, com estilo visual
  secundário em relação aos botões principais (`Button` e `OutlinedButton`).
- **D3** — O valor do contador é preservado na rotação de tela (e até em caso
  de o processo ser recriado pelo sistema), pois é salvo em um
  `SavedStateHandle` dentro da ViewModel.
- **D4** — A cor do número exibido muda conforme a faixa de valor: `error`
  para negativo (nunca ocorre por causa do D1, mas a lógica está pronta),
  `primary` para positivo e `onBackground` para zero — sempre lidas de
  `MaterialTheme.colorScheme`.
- **D5** — O estado foi extraído para `ContadorViewModel`, e a tela
  (`ContadorScreen`) é um Composable "puro" que só recebe o valor atual e
  callbacks, o que também facilita o `@Preview`.

## Estrutura do projeto

```
app/src/main/java/com/exemplo/contador/
├── MainActivity.kt          # Hospeda o tema e chama ContadorRoute
├── ContadorViewModel.kt     # Estado do contador (mutableStateOf + SavedStateHandle)
├── ContadorScreen.kt        # ContadorRoute (conectada) + ContadorScreen (pura, com @Preview)
└── ui/theme/Theme.kt        # Tema Material 3 (cores via MaterialTheme.colorScheme)
```

## Como rodar

1. Abra a pasta do projeto no Android Studio (Hedgehog ou mais recente).
2. Deixe o Android Studio sincronizar o Gradle (ele completa o *wrapper*
   automaticamente se necessário).
3. Rode no emulador ou em um dispositivo físico com Android 7.0 (API 24) ou
   superior.

