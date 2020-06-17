import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RuoloOrganizzazioneUpdateComponent } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione-update.component';
import { RuoloOrganizzazioneService } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione.service';
import { RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

describe('Component Tests', () => {
  describe('RuoloOrganizzazione Management Update Component', () => {
    let comp: RuoloOrganizzazioneUpdateComponent;
    let fixture: ComponentFixture<RuoloOrganizzazioneUpdateComponent>;
    let service: RuoloOrganizzazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RuoloOrganizzazioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RuoloOrganizzazioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RuoloOrganizzazioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RuoloOrganizzazioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RuoloOrganizzazione(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RuoloOrganizzazione();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
