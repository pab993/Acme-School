
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.CreditCardRepository;
import domain.CreditCard;
import domain.Manager;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	creditcardRepository;

	@Autowired
	private ActorService			actorService;


	public CreditCardService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public CreditCard create() {
		Assert.isTrue(this.actorService.isManager());
		CreditCard res = new CreditCard();
		res.setManager((Manager) this.actorService.findByPrincipal());

		return res;
	}

	public CreditCard findOne(int creditcardId) {
		return this.creditcardRepository.findOne(creditcardId);
	}

	public Collection<CreditCard> findAll() {
		return this.creditcardRepository.findAll();
	}

	public CreditCard save(CreditCard creditcard) {
		Assert.notNull(creditcard);
		Assert.isTrue(this.actorService.isManager());
		Assert.isTrue(creditcard.getManager().equals(this.actorService.findByPrincipal()));
		return this.creditcardRepository.save(creditcard);
	}

	public void delete(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getManager().equals(this.actorService.findByPrincipal()));
		Assert.isTrue(this.creditcardRepository.exists(creditCard.getId()));

		this.creditcardRepository.delete(creditCard);

		Assert.isTrue(!this.creditcardRepository.exists(creditCard.getId()));
	}

	public void recontruct(CreditCard creditCard, BindingResult binding) {
		// TODO Auto-generated method stub
		this.checkExpirationDate(creditCard, binding);
		//		int[] n = stringToArray(creditCard.getNumber());
		//		verificacionLuhn(n, binding);

	}

	public boolean verificacionLuhn(int[] digits, BindingResult binding) {
		int sum = 0;
		Boolean result = false;
		int length = digits.length;
		for (int i = 0; i < length; i++) {
			// sacar los digitos en orden inverso
			int digit = digits[length - i - 1];

			// cada segundo n�mero se multiplica por 2
			if (i % 2 == 1)
				digit = digit * 2;
			if (digit > 9)
				digit = digit - 9;
			sum = sum + digit;
		}
		if (sum % 10 == 0)
			result = true;
		return result;
	}

	public boolean checkExpirationDate(CreditCard creditCard, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		long l = 10;
		Calendar actualCalendar = Calendar.getInstance();
		Date actual = new Date(System.currentTimeMillis() - l);
		actualCalendar.setTime(actual);

		if (creditCard.getExpirationMonth() != null && creditCard.getExpirationYear() != null) {
			if (creditCard.getExpirationYear() == actualCalendar.get(Calendar.YEAR) && creditCard.getExpirationMonth() < actualCalendar.get(Calendar.MONTH))
				result = true;
			if (result) {
				codigos = new String[1];
				codigos[0] = "creditCard.past.month";
				error = new FieldError("creditCard", "expirationMonth", creditCard.getExpirationMonth(), false, codigos, null, "");
				binding.addError(error);
			}
		}
		return result;
	}

	public int[] stringToArray(String number) {
		char[] a = number.toCharArray();
		int[] n = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			int x = Character.getNumericValue(a[i]);
			n[i] = x;
		}
		return n;
	}

}
